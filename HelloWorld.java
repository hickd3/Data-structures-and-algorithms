public class HelloWorld{
    public static void main(String[] args){
        System.out.println("Hello World! The JDK is running");
    }
}

//# Let's breakdown how the main method works in Java. The first thing that you see is the "public" access
// modifier, this simply tells the CPU that it has access to the program and it can run it. The next thing
// is the "static" keyword. The less sufficent but wholly truthful answer is that it is required to format 
// a main method in Java. That actually goes for pretty much everything in line 2. Without the static keyword 
// the terminal will tell you to "write the main method as..."  or that it "can not find main method". I can 
// explain this later on. The next thing you see is the return type, in the main method generally it should
// be "void". This means that we are not returning anything, which would make sense becuase the purpose
// of the main method is to test the program, we just want to make sure the methods do what we intended
// for them to do. And of course we have the class title as "main" this is non-negotiable like some of the
// other words, the syntax must include main! Java finds the method through this title. Lastly you have the
// inputs that this method will take which is made clear by "args"  short for arguments and the type that 
// the method will support, "String[]". This is not sensititve, there are many variations (ways to write) "(String[] args)"
// that can be used but this is the most common.

//The static keyword in this context is used so that the computer can run the java program without needing to instantiate objects. 
//Put simply this means that the main method will be able to test the methods in your program. Why? Well the "static" keyword
//belongs to the class rather than an instance of a class i.e. here the method doesn't need an object to reference.
//With the static keyword I can directly access class methods and class variables. I am allowed to create obejcts in the main method becuase
//static allows me to make class variables that can be manipulated by any object and otherwise inherit all the information that is 
//coded outside the main method.

//The reason this is the way that it is, is because of how the JDK complies a program to run. That explanation is not complex at all and has everything to
//do with Java's nature as a programming language. Additionally, the OOP is far less complex that it seems. I believe that its definitivness and uncompromising structure
//makes it a little hard to catch onto at first because its just so procedural and rigid. 