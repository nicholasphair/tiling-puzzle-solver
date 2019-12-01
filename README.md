# tiling-puzzle-solver
<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [overview](#overview)
- [building](#building)
- [running](#running)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->
# overview
Puzzles are solved by expressing them as exact cover problems. From there, we use
Donald Knuth's Algorithm X with dancing links (DLX), to solve the exact cover problem.

# building
You can build the project with maven:
```bash
mvn clean install
```

# running
```bash
java -jar target/tiling-puzzle-solver-0.0.1-SNAPSHOT.jar
```
