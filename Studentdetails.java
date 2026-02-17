public class Studentdetails{
    public static void main (String[] args){
        String[] names={"sona","reya","rekha","zoya","ram"};
        int[]marks={75,34,56,97,96};
        System.out.println("Studentdetails:");
        for(int i=0;i<5;i++){
        String grade;
            if(marks[i]>=90)
                grade= "A";
            else if(marks[i]>=75)
                grade="B";
            else if(marks[i]>=65)
                grade="C";
            else if(marks[i]>=40)
                grade="D";
            else
                grade="fail";      

            System.out.println("Name : " + names[i]);
            System.out.println("Marks: " + marks[i]);
            System.out.println("Grade: " + grade);
            System.out.println("-------");
            
        }
        
        }
        
}
