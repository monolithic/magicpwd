if [ -e "magicpwd.desktop" ]
then
echo "#!/usr/bin/env xdg-open" > magicpwd.desktop
else
echo "#!/usr/bin/env xdg-open" >> magicpwd.desktop
fi

echo "" >> magicpwd.desktop
echo "[Desktop Entry]" >> magicpwd.desktop
echo "Encoding=UTF-8" >> magicpwd.desktop
echo "Version=1.0" >> magicpwd.desktop
echo "Type=Application" >> magicpwd.desktop
echo "Terminal=false" >> magicpwd.desktop
echo "Icon[zh_CN]=evince" >> magicpwd.desktop
echo "Exec=$(pwd)/magicpwd.jar" >> magicpwd.desktop
echo "Name[zh_CN]=魔方密码" >> magicpwd.desktop
echo "Comment[zh_CN]=魔方密码" >> magicpwd.desktop
echo "Name=魔方密码" >> magicpwd.desktop
echo "Comment=魔方密码" >> magicpwd.desktop
echo "Icon=evince" >> magicpwd.desktop
echo "" >> magicpwd.desktop

if [ -d ~/desktop/ ]
then
mv magicpwd.desktop ~/desktop/magicpwd.desktop
chmod 711 ~/desktop/magicpwd.desktop
fi

if [ -d ~/桌面/ ]
then
mv magicpwd.desktop ~/桌面/magicpwd.desktop
chmod 711 ~/桌面/magicpwd.desktop
fi