Set oFso = CreateObject("Scripting.FileSystemObject")
strCurPath = oFso.GetParentFolderName(Wscript.ScriptFullName)

Set WshShell = WScript.CreateObject("WScript.Shell")
strDesktop = WshShell.SpecialFolders("Desktop") :'特殊文件夹“桌面”

Rem 在桌面创建一个记事本快捷方式
set oLnk = WshShell.CreateShortcut(strDesktop & "\魔方密码.lnk")
oLnk.TargetPath = strCurPath & "\magicpwd.jar" : '目标
oLnk.WindowStyle = 1 :'参数1默认窗口激活，参数3最大化激活，参数7最小化
oLnk.Hotkey = "Ctrl+Alt+M" : '快捷键
oLnk.IconLocation = strCurPath & "\logo\logo.ico, 0" : '图标
oLnk.Description = "魔方密码，跨平台密码管理软件！" : '备注
oLnk.WorkingDirectory = strCurPath & "\" : '起始位置
oLnk.Save : '创建保存快捷方式

Rem 在桌面创建一个“微软中国”的Url快捷方式
set oUrl = WshShell.CreateShortcut(strDesktop & "\魔方密码首页.url")
oUrl.TargetPath = "http://magicpwd.com/"
oUrl.Hotkey = "Ctrl+Alt+H"
oUrl.Save