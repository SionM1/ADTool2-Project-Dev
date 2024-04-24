Overview
The Attack Defense Tree Tool (ADTool) facilitates the modelling and visualization of attack-defence scenarios through attack defence trees (ADTrees) or a term-based representation known as attack defence terms (ADTerms). Originally developed within the ATREES project, ADTool is versatile, supporting not only ADTrees but also protection trees, and defence trees under the umbrella of attack defence trees.

Recently, the tool has been significantly enhanced to include modelling with Sequential Attack Trees, integrating the MITRE ATT&CK framework to enrich the contextual analysis capabilities:

MITRE ATT&CK Framework Integration: Allows users to leverage a comprehensive, globally accessible knowledge base of adversary tactics and techniques based on real-world observations. This integration provides a more detailed and structured approach in modelling attack scenarios and enriching them with standardized technique information.
Enhanced Node Evaluation: Incorporates advanced evaluation mechanisms that assess the impact, cost, and feasibility of attack scenarios within the defined frameworks. This aids in a more nuanced understanding and decision-making process in security planning.
User Interface Improvements: Improvements to the user interface ensure that the tool not only remains intuitive but also becomes more adaptable to various user interactions, enhancing the overall user experience.
Using ADTool
Model complex attack-defence scenarios using both ADTrees and ADTerms.
Perform quantitative analyses to evaluate scenarios based on various metrics such as cost, time, and required attacker skill level.
Utilize the integrated MITRE ATT&CK framework to apply real-world attack techniques and tactics to your models.
Compilation and Setup
ADTool is developed in Java and requires Maven for compilation. Here are the steps to compile and run the tool:

## Compilation

ADTool is written in Java. In order to compile the source code a
[Maven](https://maven.apache.org/) tool is necessary. After you have downloaded
and installed maven tool:
- clone the github repository: (`git clone git://github.com/tahti/ADTool2.git`)
- goto `project` directory (`cd project`)
- compile using maven (`mvn package`)
- after successful compilation there should be new directory `target` with ADTool-2.2-full.jar among other files
- run the tool using command `java -jar target/ADTool-2.2-full.jar`

The latest compiled jar file can be downloaded from
[here](http://satoss.uni.lu/members/piotr/adtool/) - look in Download section. 

