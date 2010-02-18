Set oFso = CreateObject("Scripting.FileSystemObject")
strCurPath = oFso.GetParentFolderName(Wscript.ScriptFullName)

Set WshShell = WScript.CreateObject("WScript.Shell")
strDesktop = WshShell.SpecialFolders("Desktop")

set oLnk = WshShell.CreateShortcut(strDesktop & "\魔方密码.lnk")
oLnk.TargetPath = strCurPath & "\magicpwd.jar"
oLnk.WindowStyle = 1
oLnk.Hotkey = "Ctrl+Alt+M"
oLnk.IconLocation = strCurPath & "\logo\logo.ico, 0"
oLnk.Description = "魔方密码，跨平台密码管理软件！"
oLnk.WorkingDirectory = strCurPath & "\"
oLnk.Save

set oUrl = WshShell.CreateShortcut(strDesktop & "\魔方密码首页.url")
oUrl.TargetPath = "http://magicpwd.com/"
oUrl.Hotkey = "Ctrl+Alt+H"
oUrl.Save