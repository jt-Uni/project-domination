#!/bin/bash

# Change directory to the project's root directory
cd /Users/rafiksongoku/Documents/project-domination

# Run the Java application with the agent, and redirect the output to a log file
/Users/rafiksongoku/Library/Java/JavaVirtualMachines/openjdk-22/Contents/Home/bin/java \
-javaagent:"/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=49399:/Applications/IntelliJ IDEA.app/Contents/bin" \
-Dfile.encoding=UTF-8 \
-Dsun.stdout.encoding=UTF-8 \
-Dsun.stderr.encoding=UTF-8 \
-classpath target/classes \
core.GameApp \
> run.log 2>&1
