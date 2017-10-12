
#include <AutoItConstants.au3>
#include <GuiListBox.au3>
#Include <GuiComboBox.au3>
#Include <GuiComboBoxEX.au3>
#include <GUIConstantsEx.au3>
#include <Date.au3>
#include <MsgBoxConstants.au3>
#include <MsgBoxConstants.au3>
#include <File.au3>

Local $TempFolderPath = "C:\Users\SESA439753\git-local-repository\ssowindchillaccessrightsautomation\WindChillAccessRightsAutomation\temp"

Global $UploadFilepath

;Username from the text file
Local $FileName = $TempFolderPath&"\"&"uploadfilepath.txt"


Func ReadFile()

    ; Open the file for reading and store the handle to a variable.
    Local $hFileOpen = FileOpen($FileName, $FO_READ)
    If $hFileOpen = -1 Then
        MsgBox($MB_SYSTEMMODAL, "", "An error occurred when reading the file.")
        Return False
    EndIf

    ; Read the fist line of the file using the handle returned by FileOpen.
    $UploadFilepath = FileReadLine($hFileOpen, 1)

    ; Close the handle returned by FileOpen.
    FileClose($hFileOpen)

    ; Display the first line of the file.
    ;MsgBox($MB_SYSTEMMODAL, "", "First line of the file:" & @CRLF & $CADFileStatusValue)

   EndFunc


 ReadFile()
  ; MsgBox($MB_SYSTEMMODAL, "", $UploadFilepath,1)


Local $hWnd = WinWait("[CLASS:Edit]", "", 2)
ControlFocus("Title","Open","Edit1")

ControlClick("Open","","Edit1")
Sleep(1000)
ControlSetText("Open","","Edit1",$UploadFilepath)
;ControlSetText("Open","","Edit1","C:\Users\SESA439753\SchneiderAutomation\WindChillAccessRightsAutomation\AutoITFiles\my pom.txt")
ControlClick("Open","","Button1")