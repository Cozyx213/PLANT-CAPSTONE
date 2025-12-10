# Compile sources
javac -d out $(find src/main/java -name "*.java")

# Ensure resources are on the runtime classpath
mkdir -p out
cp -R src/main/resources/* out/

# Run with classes + resources
java -cp out com.plantfarmlogger.Main