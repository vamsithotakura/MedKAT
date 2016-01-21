package org.ohnlp.medkat.taes.gradeDetector;


//constants used by GradeDetector and by MedKATConsumer
public class Grades {
	//public static final int NO_GRADE=0;
	//this order now matches that in the LDM
	public static final int HIST_GRADE=0;
	public static final int NUC_GRADE=1;
	public static final int PRIMARY_GLEASON_GRADE=2;
	public static final int SECONDARY_GLEASON_GRADE=3;
	public static final int GLEASON_GRADE=4;
	public static final int OTHER_GRADE=11;
	public static final int FIGO_GRADE=6;
	public static final int FUHRMAN_GRADE = 7;
	public static final int STEINER_GRADE = 5;
	public static final int WHO_GRADE = 8;
	public static final int FOCAL_GRADE=9;
	public static final int PANIN_GRADE =10;
	

	
	//these are the default values but they are also 
	//set by GradeDetector from the descriptor file
	
	public static String  primaryGleasonGradeLabel= "PrimaryGleasonsGrade";
    public static String secondaryGleasonGradeLabel = "SecondaryPrimaryGleasonsGrade";
    public static String totalGleasonGradeLabel= "GleasonsGrade";

    //These values are declared here as well as in the grades.xml dictionary
    public static String histLabel = "histologic";
    public static String nucLabel = "nuclear";
    public static String figoLabel = "figo";
    public static String fuhrmanLabel = "fuhr";
    public static String steinerLabel = "steiner";
    public static String whoLabel = "who";
    
}
