/**
 *
 * @author Sama Rahimian
 * Olga Veksler
 * 2210b
 *
 * This exception should be thrown by your dictionary in case of unexpected
 * conditions, see section 6 for cases in which to throw this exception.
 *
 */


public class DictionaryException extends Exception{
//    public DictionaryException(){
//
//        super("Error: Unexpected conditions.");
//    }

    public DictionaryException(String x){
        super(x);
    }
    
}
