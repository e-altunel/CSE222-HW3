JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = $(wildcard *.java)

all : InventoryManager.class

run : InventoryManager.class
	@echo "\033[1;32mRunning InventoryManager...\033[0m"
	@$(JR) InventoryManager

classes : $(CLASSES:.java=.class)

%.class : %.java $(CLASSES)
	@echo "\033[1;32mCompiling $<...\033[0m"
	@$(JC) $(JFLAGS) $<

doc:
	@echo "\033[1;32mGenerating Javadoc...\033[0m"
	@$(JD) $(JDFLAGS) *.java 

clean:
	@echo "\033[1;32mCleaning up...\033[0m"
	@$(RM) *.class 

cleandoc:
	@echo "\033[1;32mCleaning up Javadoc...\033[0m"
	@$(RM) -r ./javadoc

.PHONY: all run classes doc clean cleandoc