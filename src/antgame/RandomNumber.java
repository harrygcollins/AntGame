/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;
import java.math.BigInteger;

/*
 *
 * @author Team13
 */
public class RandomNumber {

    public int s; //initial seed
    public int noOfCalls; //where in the sequence are we
    
    public RandomNumber(int s) {
        this.s = s;
        noOfCalls = 0;
    }
    
    //Method to be called to get the next random int in the sequence
    public int randomInt(int n) {
        BigInteger num = new BigInteger("" + getS(noOfCalls + 4) + ""); //make a call to a seperate method due to repetition
        BigInteger x = num.divide(new BigInteger("65536")); //(si+4 div 65536)
        x = x.mod(new BigInteger("16384"));
        noOfCalls++; //Increment the number of calls for future method calls
        
        String resultString = x.toString(); //Convert BigInteger into a String object
        int result = Integer.parseInt(resultString); //String to int
        
        return result%n; //Final modulous operation
    }
    
    //Works with the first phase of the sequence, uses BigInteger because it has no max unlike int and long
    public BigInteger getS(int i) {
        BigInteger temp = new BigInteger("" + s + ""); // BigInteger only takes String as a parameter in the constructor
        for (int j=1;j<=i;j++) { // implements (si+1 = (si x 22695477) + 1)
            temp = temp.multiply(new BigInteger("22695477"));
            temp = temp.add(new BigInteger("1"));
        }
        return temp; //returns the BigInteger to first method
    }
}