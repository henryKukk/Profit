*** Settings ***
Library           Selenium2Library

*** Variables ***
${FirstName}      Henry
${LastName}       Testing
${Password}       testPWD
${Username}       testUser
${date}           2016/03/03
${Browser}        Firefox
${SiteUrl}        http://localhost:8080
${Delay}          2s

*** Test Cases ***
AddUserTest
    Open Browser LocalHost
    Click Element    //*[@id="addUserDiv"]/button
    sleep    ${Delay}
    Enter First Name
    Enter Last Name
    Add User
    Assert Table Has Person

EditTest
    sleep    ${Delay}
    Edit user
    Check Edit

DeleteTest
    sleep    ${Delay}
    Delete user
    Assert Page Doenst Have
    [Teardown]    Close Browser

*** Keywords ***
Check Edit
    Page Should Not Contain    Henry
    Page Should Not Contain    Testing
    Table Should Contain    xpath=//body/table    EESUUS
    Table Should Contain    xpath=//body/table    PEREKONNAUUS

Edit user
    Click Element    //*[@id="users"]/tr/td[8]/button
    sleep    1s
    Input Text    first_name_edit    EESUUS
    Input Text    last_name_edit    PEREKONNAUUS
    Click Element    //*[@id="edit_user"]

Open Browser LocalHost
    open browser    ${SiteUrl}    ${Browser}

Enter First Name
    Input Text    first_name    ${FirstName}
    Input Text    dob    ${date}
    Input Text    username    ${Username}
    Input Text    password    ${Password}

Enter Last Name
    Input Text    last_name    ${LastName}

Add User
    Click Element    //*[@id="first_name"]
    Sleep    1s
    Click Element    //*[@id="add_user"]

Assert Table Has Person
    Page Should Contain Element    id=users
    Table Should Contain    xpath=//body/table    Henry
    Table Should Contain    xpath=//body/table    Testing

Assert Page Doenst Have
    Page Should Not Contain    Henry
    Page Should Not Contain    Testing

Delete user
    Click Element    //*[@id="users"]/tr/td[7]/button
