#!/bin/bash

# Change directory to the project's root directory
cd /Users/rafiksongoku/Documents/project-domination

# Run the Java application with the agent, and redirect the output to a log file
/Users/rafiksongoku/Library/Java/JavaVirtualMachines/openjdk-22/Contents/Home/bin/java \
-javaagent:"/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=51512:/Applications/IntelliJ IDEA.app/Contents/bin" \
-Dfile.encoding=UTF-8 \
-Dsun.stdout.encoding=UTF-8 \
-Dsun.stderr.encoding=UTF-8 \
-classpath target/classes \
core.GameApp \
> run.log 2>&1


## when you run this on your machine make sure to update the path to you source root directory where the project is, then run it up
## for the test log, it wont show nothing as it is graphics user game, so it only show some window warning, and all aspects of the game.
# thank you