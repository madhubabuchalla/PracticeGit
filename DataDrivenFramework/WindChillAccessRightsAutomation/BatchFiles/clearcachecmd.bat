@echo off
Rem Temp File Purging Tool v1.2.0
Rem
Rem This script deletes temp files in locations where malware likes to write its initial
Rem files for infection and also where standard users have write permissions.
Rem
Rem This tool isn't likely to be as helpful to clean systems on which users run with 
Rem Admin permissions.  If you let your users run with Admin permissions you by extension
Rem give much of the malware on the Internet permission to do as it pleases on your workstations.



    Rem It will work for all versions of Windows After Win 7


:SetUserProfPath
   
        Set UserProfileRootPath=C:\Users
   
    Call :RemoveSubfoldersAndFiles %SystemRoot%\Temp

    Rem Walk through each user profile folder
    Rem This convoluted command is necessary to ensure we process hidden and system folders too
    for /f "delims=" %%D in ('dir /ad /b "%UserProfileRootPath%"') DO Call :ProcessProfileFolder %UserProfileRootPath%\%%D

   exit 0

goto :EOF


:ProcessProfileFolder

    Set FolderName=%*

    Rem Leave if it's not a user profile folder
    If Not Exist "%FolderName%\ntuser.dat" goto :EOF

    Rem Leave it's a profile folder on the exclude list
    If /I "%FolderName%" EQU "%UserProfileRootPath%\Default" goto :EOF
    If /I "%FolderName%" EQU "%UserProfileRootPath%\Default User" goto :EOF
    If /I "%FolderName%" EQU "%UserProfileRootPath%\NetworkService" goto :EOF
    If /I "%FolderName%" EQU "%UserProfileRootPath%\LocalService" goto :EOF

    Set UserProfilePath=%FolderName%

    Rem Clean up these folders
    
        Call :RemoveSubfoldersAndFiles %UserProfilePath%\AppData\Local\Temp
        Call :RemoveSubfoldersAndFiles %UserProfilePath%\AppData\LocalLow\Temp
        Call :RemoveSubfoldersAndFiles %UserProfilePath%\AppData\LocalLow\Sun\Java\Deployment\cache
        Call :RemoveSubfoldersAndFiles %UserProfilePath%\AppData\Local\Microsoft\Windows\Temporary Internet Files

    
goto :EOF


:RemoveSubfoldersAndFiles

    Set FolderRootPath=%*

    Rem Confirm target folder exists
    If Not Exist "%FolderRootPath%" Goto :EOF

    Rem Make the folder to clean current and confirm it exists...
    CD /D %FolderRootPath%

    Rem Confirm we switched directories
    If /I "%CD%" NEQ "%FolderRootPath%" Goto :EOF

    Rem ...so that this command cannot delete the folder, only everything in it
    Echo Purging %CD%
    RD /S /Q . >>nul 2>>&1


goto :EOF