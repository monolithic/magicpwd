@echo off
@set "cp=%cd%\magicpwd.jar"
@set "dp=%homedrive%%homepath%\桌面\魔方密码0.lnk"
@set "ip=%cd%\logo\logo.ico"
@set "dt=魔方密码，跨平台密码管理软件！"
@os\win\ShortCut.exe /F:"%dp%" /A:C /T:"%cp%" /R:1 /I:"%ip%",0 /D:"%dt%"
@os\win\MessageBox.exe	/C:安装成功 /M:魔方密码安装成功！ /W:10 /T:64