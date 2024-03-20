wget http://dl.google.com/android/repository/build-tools_r34-linux.zip
wget https://github.com/distriqt/android-manifest-merger/releases/download/v30.0.1/manifest-merger-30.0.1.jar
unzip build-tools_r34-linux.zip
export PATH=android-14:$PATH
mkdir build
mkdir build/apk
mkdir build/classes
aapt2 compile --dir app/src/main/res -o build/res.zip
aapt2 compile --dir Library/src/main/res -o build/libres.zip
java -jar manifest-merger-30.0.1.jar --main app/src/main/AndroidManifest.xml --libs Library/src/main/AndroidManifest.xml --out build/AndroidManifest.xml --property MIN_SDK_VERSION=26
aapt2 link --version-code 1 -I app/src/main/assets/android.jar --allow-reserved-package-id --no-version-vectors --no-version-transitions --min-sdk-version 26 --target-sdk-version 28 -R build/res.zip -R build/libres.zip --manifest build/AndroidManifest.xml -o build/apk/app.zip --auto-add-overlay --java app/src/main/java -A app/src/main/assets --extra-packages com.google.android.material
javac -cp app/src/main/assets/android.jar:app/libs/nb-javac.jar:Library/libs/mergeClassesJar.jar:Library/libs/mergeLocalJars.jar -d build/classes $(find app/src/main/java -name *.java)
d8 --lib app/src/main/assets/android.jar --min-api 26 $(find build/classes/ -type f) app/libs/nb-javac.jar app/libs/bin.jar Library/libs/mergeClassesJar.jar Library/libs/mergeLocalJars.jar --output build/dex.zip
cd build/apk
unzip -o ../../app/libs/nb-javac.jar -x *.class
unzip -o ../../Library/libs/mergeLocalJars.jar -x *.class
unzip -o ../../Library/libs/mergeClassesJar.jar -x *.class
unzip -o ../dex.zip
unzip -o app.zip
rm -
dir=$PWD
DeleteEmpty() {
    #find ${1:-.} -mindepth 1 -maxdepth 1 -type d | while read -r dir
	find ${1:-$dir_name} -mindepth 1 -maxdepth 1 -type d | while read -r dir
	do
        if [[ -z "$(find "$dir" -mindepth 1 -type f)" ]] >/dev/null
	then
            rm -rf ${dir} 2>&-
        fi
        if [ -d ${dir} ]
        then
            DeleteEmpty "$dir"
        fi
    done
}
DeleteEmpty
rm app.zip
7z a ../app.zip ./
cd ..
mv app.zip app.apk
