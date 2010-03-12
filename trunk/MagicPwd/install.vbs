Set oFso = CreateObject("Scripting.FileSystemObject")
strCurPath = oFso.GetParentFolderName(Wscript.ScriptFullName)

Set WshShell = WScript.CreateObject("WScript.Shell")
StrPath = "" & WshShell.Environment("user").Item("CLASSPATH")
if InStr(LCase(StrPath), LCase(strCurPath)) < 1 Then
WshShell.Environment("user").Item("CLASSPATH") = strCurPath & "\lib\;" & StrPath
End If

StrDesktop = WshShell.SpecialFolders("Desktop")

Set oLnk = WshShell.CreateShortcut(StrDesktop & "\Ä§·½ÃÜÂë.lnk")
oLnk.TargetPath = strCurPath & "\magicpwd.jar"
oLnk.WindowStyle = 1
oLnk.Hotkey = "Ctrl+Alt+M"
oLnk.IconLocation = strCurPath & "\logo\logo.ico, 0"
oLnk.Description = "Ä§·½ÃÜÂë£¬¿çÆ½Ì¨ÃÜÂë¹ÜÀíÈí¼þ£¡"
oLnk.WorkingDirectory = strCurPath & "\"
oLnk.Save

Set oUrl = WshShell.CreateShortcut(StrDesktop & "\Ä§·½ÃÜÂëÊ×Ò³.url")
oUrl.TargetPath = "http://magicpwd.com/"
oUrl.Save