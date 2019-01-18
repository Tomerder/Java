
package aiex4;

import java.util.HashMap;
import java.util.Hashtable;


public class Frequency
{

	private static Frequency	_instance;
	private HashMap < Integer , Double > _wordLengths;
	private HashMap < Character , Double > _monogramFrequencies;
	private HashMap < String , Double > _bigramsIncludingSpace;
	private HashMap < String , Double > _trigramsIncludingSpace; 

	private Frequency ( )
	{
		/////////////////////Word lengths
		_wordLengths=new HashMap< Integer , Double >();
		_wordLengths.put (  1  , 4.40 );
		_wordLengths.put (  2  , 17.33 );
		_wordLengths.put (  3  , 24.86 );
		_wordLengths.put (  4  , 18.16 );
		_wordLengths.put (  5  , 10.77  );
		_wordLengths.put (  6  , 7.99 );
		_wordLengths.put (  7  , 6.41  );
		_wordLengths.put (  8  , 4.21  );
		_wordLengths.put (  9  , 2.76 );
		_wordLengths.put (  10  , 1.65 );
		_wordLengths.put (  11  , 0.71 );
		_wordLengths.put (  12  , 0.45 );
		_wordLengths.put (  13  , 0.18  );
		_wordLengths.put (  14  , 0.10  );
		_wordLengths.put (  15  , 0.02  );
		_wordLengths.put (  16  , 0.01  );
		_wordLengths.put (  0  , 0.0  );
		
		
		
		
		////////////////////////Monogram frequencies
		_monogramFrequencies=new HashMap< Character , Double >();
		_monogramFrequencies.put ( 'a' ,6.61  );
		_monogramFrequencies.put ( 'b' , 1.15  );
		_monogramFrequencies.put ( 'c' , 1.92  );
		_monogramFrequencies.put ( 'd' , 3.69  );
		_monogramFrequencies.put ( 'e' , 10.17  );
		_monogramFrequencies.put ( 'f' , 1.75  );
		_monogramFrequencies.put ( 'g' , 1.61  );
		_monogramFrequencies.put ( 'h' , 5.42  );
		_monogramFrequencies.put ( 'i' , 5.62  );
		_monogramFrequencies.put ( 'j' , 0.08  );
		_monogramFrequencies.put ( 'k' , 0.660  );
		_monogramFrequencies.put ( 'l' , 3.25  );
		_monogramFrequencies.put ( 'm' , 2.05  );
		_monogramFrequencies.put ( 'n' , 5.57  );
		_monogramFrequencies.put ( 'o' , 6.10  );
		_monogramFrequencies.put ( 'p' , 1.31  );
		_monogramFrequencies.put ( 'q' , 0.08  );
		_monogramFrequencies.put ( 'r' , 4.58  );
		_monogramFrequencies.put ( 's' , 5.08  );
		_monogramFrequencies.put ( 't' , 7.37  );
		_monogramFrequencies.put ( 'u' , 2.28  );
		_monogramFrequencies.put ( 'v' , 0.880  );
		_monogramFrequencies.put ( 'w' , 1.90  );
		_monogramFrequencies.put ( 'x' , 0.14  );
		_monogramFrequencies.put ( 'y' , 1.65  );
		_monogramFrequencies.put ( 'z' , 0.05  );
		_monogramFrequencies.put ( ' ' , 19.04  );
		
		
		
		//////////////////////Most common bigrams including space
		_bigramsIncludingSpace=new HashMap< String , Double >();
		_bigramsIncludingSpace.put ( "e " ,3.81  );
		_bigramsIncludingSpace.put ( " m" , 0.85 );
		_bigramsIncludingSpace.put ( "h " , 0.53  );
		_bigramsIncludingSpace.put ( " t" , 2.93  );
		_bigramsIncludingSpace.put ( "at" , 0.85  );
		_bigramsIncludingSpace.put ( "me" , 0.52  );
		_bigramsIncludingSpace.put ( "he" , 2.46  );
		_bigramsIncludingSpace.put ( "on" , 0.84  );
		_bigramsIncludingSpace.put ( " p" , 0.52  );
		_bigramsIncludingSpace.put ( "th" , 2.41  );
		_bigramsIncludingSpace.put ( " b" , 0.82  );
		_bigramsIncludingSpace.put ( "nt" , 0.52  );
		
		_bigramsIncludingSpace.put ( "d " , 2.36  );
		_bigramsIncludingSpace.put ( "hi" , 0.8  );
		_bigramsIncludingSpace.put ( "ea" , 0.51  );
		_bigramsIncludingSpace.put ( " a" , 2.14  );
		_bigramsIncludingSpace.put ( "en" , 0.79  );
		_bigramsIncludingSpace.put ( "al" , 0.49  );
		
		
		_bigramsIncludingSpace.put ( "t " , 2.04  );
		_bigramsIncludingSpace.put ( "to" , 0.76  );
		_bigramsIncludingSpace.put ( " l" , 0.49  );
		_bigramsIncludingSpace.put ( "s " , 1.980  );
		_bigramsIncludingSpace.put ( "ng" , 0.750  );
		_bigramsIncludingSpace.put ( "l " , 0.49  );
		
		
		_bigramsIncludingSpace.put ( " h" , 1.61  );
		_bigramsIncludingSpace.put ( " c" , 0.73  );
		_bigramsIncludingSpace.put ( "a " , 0.48  );
		_bigramsIncludingSpace.put ( " s" , 1.52  );
		_bigramsIncludingSpace.put ( "is" , 0.73  );
		_bigramsIncludingSpace.put ( "ll" , 0.48  );
		
		
		_bigramsIncludingSpace.put ( "in" , 1.47  );
		_bigramsIncludingSpace.put ( "it" , 0.73  );
		_bigramsIncludingSpace.put ( "ne" , 0.46  );
		_bigramsIncludingSpace.put ( "n " , 1.4  );
		_bigramsIncludingSpace.put ( " f" , 0.68  );
		_bigramsIncludingSpace.put ( " n" , 0.44  );
		_bigramsIncludingSpace.put ( "an" , 1.39  );
		_bigramsIncludingSpace.put ( "or" , 0.67  );
		_bigramsIncludingSpace.put ( "ti" , 0.43  );
		_bigramsIncludingSpace.put ( " w" , 1.35  );
		_bigramsIncludingSpace.put ( " f" , 0.66  );
		_bigramsIncludingSpace.put ( "de" , 0.42  );
		
		
		_bigramsIncludingSpace.put ( "er" , 1.31  );
		_bigramsIncludingSpace.put ( "as" , 0.64  );
		_bigramsIncludingSpace.put ( "no" , 0.42  );
		_bigramsIncludingSpace.put ( " i" , 1.22  );
		_bigramsIncludingSpace.put ( "g " , 0.63  );
		_bigramsIncludingSpace.put ( "be" , 0.4  );
		_bigramsIncludingSpace.put ( "r " , 1.11  );
		_bigramsIncludingSpace.put ( "te" , 0.63  );
		_bigramsIncludingSpace.put ( "ro" , 0.4  );
		_bigramsIncludingSpace.put ( "re" , 1.08  );
		_bigramsIncludingSpace.put ( "es" , 0.62  );
		_bigramsIncludingSpace.put ( " r" , 0.4  );
		
		_bigramsIncludingSpace.put ( " o" , 1.08  );
		_bigramsIncludingSpace.put ( " d" , 0.61  );
		_bigramsIncludingSpace.put ( "wa" , 0.39  );
		_bigramsIncludingSpace.put ( "y " , 1.08  );
		_bigramsIncludingSpace.put ( "ar" , 0.59  );
		_bigramsIncludingSpace.put ( "wh" , 0.39  );
		_bigramsIncludingSpace.put ( "nd" , 1.01  );
		_bigramsIncludingSpace.put ( "st" , 0.59  );
		_bigramsIncludingSpace.put ( "m " , 0.39  );
		_bigramsIncludingSpace.put ( "o " , 0.95  );
		_bigramsIncludingSpace.put ( "le" , 0.58  );
		_bigramsIncludingSpace.put ( "ho" , 0.39  );
		
		_bigramsIncludingSpace.put ( "ou" , 0.93  );
		_bigramsIncludingSpace.put ( "se" , 0.57  );
		_bigramsIncludingSpace.put ( " y" , 0.38  );
		_bigramsIncludingSpace.put ( "ha" , 0.91  );
		_bigramsIncludingSpace.put ( "of" , 0.59  );
		_bigramsIncludingSpace.put ( "el" , 0.38  );
		_bigramsIncludingSpace.put ( "ed" , 0.88  );
		_bigramsIncludingSpace.put ( "ve" , 0.55  );
		_bigramsIncludingSpace.put ( "ad" , 0.37  );
		

		
		//Most common trigrams including space 
		_trigramsIncludingSpace=new HashMap< String , Double >();
		_trigramsIncludingSpace.put ( " th" ,1.95  );
		_trigramsIncludingSpace.put ( " wh" , 0.39 );
		_trigramsIncludingSpace.put ( "or " , 0.27  );
		_trigramsIncludingSpace.put ( "he " , 1.58  );
		_trigramsIncludingSpace.put ( "re " , 0.38  );
		_trigramsIncludingSpace.put ( "me " , 0.27  );
		_trigramsIncludingSpace.put ( "the" , 1.53  );
		_trigramsIncludingSpace.put ( " a " , 0.36  );
		_trigramsIncludingSpace.put ( "e h" , 0.27  );
		_trigramsIncludingSpace.put ( "ed " , 0.82  );
		_trigramsIncludingSpace.put ( "e s" , 0.36  );
		_trigramsIncludingSpace.put ( "d a" , 0.26  );
		
		_trigramsIncludingSpace.put ( "nd " , 0.8  );
		_trigramsIncludingSpace.put ( "hat" , 0.35  );
		_trigramsIncludingSpace.put ( " sh" , 0.26  );
		_trigramsIncludingSpace.put ( " an" , 0.78  );
		_trigramsIncludingSpace.put ( "on " , 0.35  );
		_trigramsIncludingSpace.put ( "for" , 0.25  );
		_trigramsIncludingSpace.put ( "and" , 0.75  );
		_trigramsIncludingSpace.put ( "e a" , 0.35  );
		_trigramsIncludingSpace.put ( "ut " , 0.25  );
		_trigramsIncludingSpace.put ( " to" , 0.62  );
		_trigramsIncludingSpace.put ( " be" , 0.34  );
		_trigramsIncludingSpace.put ( "s t" , 0.25  );
		
		_trigramsIncludingSpace.put ( "nd " , 0.8  );
		_trigramsIncludingSpace.put ( "hat" , 0.35  );
		_trigramsIncludingSpace.put ( " sh" , 0.26  );
		_trigramsIncludingSpace.put ( " an" , 0.78  );
		_trigramsIncludingSpace.put ( "on " , 0.35  );
		_trigramsIncludingSpace.put ( "for" , 0.25  );
		_trigramsIncludingSpace.put ( "and" , 0.75  );
		_trigramsIncludingSpace.put ( "e a" , 0.35  );
		_trigramsIncludingSpace.put ( "ut " , 0.25  );
		_trigramsIncludingSpace.put ( " to" , 0.62  );
		_trigramsIncludingSpace.put ( " be" , 0.34  );
		_trigramsIncludingSpace.put ( "s t" , 0.25  );
		
		_trigramsIncludingSpace.put ( "ng " , 0.62  );
		_trigramsIncludingSpace.put ( "n t" , 0.33  );
		_trigramsIncludingSpace.put ( "it " , 0.25  );
		_trigramsIncludingSpace.put ( " he" , 0.61  );
		_trigramsIncludingSpace.put ( "his" , 0.33  );
		_trigramsIncludingSpace.put ( "ere" , 0.25  );
		_trigramsIncludingSpace.put ( "er " , 0.6  );
		_trigramsIncludingSpace.put ( "t t" , 0.32  );
		_trigramsIncludingSpace.put ( " sa" , 0.24  );
		_trigramsIncludingSpace.put ( "ing" , 0.59  );
		_trigramsIncludingSpace.put ( " wa" , 0.32  );
		_trigramsIncludingSpace.put ( " it" , 0.24  );
		
		_trigramsIncludingSpace.put ( "to " , 0.59  );
		_trigramsIncludingSpace.put ( " yo" , 0.32  );
		_trigramsIncludingSpace.put ( "ou " , 0.24  );
		_trigramsIncludingSpace.put ( " of" , 0.53  );
		_trigramsIncludingSpace.put ( "you" , 0.32  );
		_trigramsIncludingSpace.put ( " fo" , 0.24  );
		_trigramsIncludingSpace.put ( "at " , 0.52  );
		_trigramsIncludingSpace.put ( "e w" , 0.31  );
		_trigramsIncludingSpace.put ( "an " , 0.24  );
		_trigramsIncludingSpace.put ( "of " , 0.51  );
		_trigramsIncludingSpace.put ( " no" , 0.31  );
		_trigramsIncludingSpace.put ( "was" , 0.23  );
	
		_trigramsIncludingSpace.put ( "is " , 0.46  );
		_trigramsIncludingSpace.put ( "en " , 0.31  );
		_trigramsIncludingSpace.put ( " re" , 0.23  );
		_trigramsIncludingSpace.put ( "d t" , 0.44  );
		_trigramsIncludingSpace.put ( " co" , 0.31  );
		_trigramsIncludingSpace.put ( "e c" , 0.23  );
		_trigramsIncludingSpace.put ( " in" , 0.44  );
		_trigramsIncludingSpace.put ( " wi" , 0.3  );
		_trigramsIncludingSpace.put ( " on" , 0.23  );
		_trigramsIncludingSpace.put ( " hi" , 0.42  );
		_trigramsIncludingSpace.put ( "tha" , 0.3  );
		_trigramsIncludingSpace.put ( "th " , 0.23  );
		
		_trigramsIncludingSpace.put ( " ha" , 0.41  );
		_trigramsIncludingSpace.put ( "ll " , 0.3  );
		_trigramsIncludingSpace.put ( " ma" , 0.23  );
		_trigramsIncludingSpace.put ( "e t" , 0.41  );
		_trigramsIncludingSpace.put ( " es" , 0.28  );
		_trigramsIncludingSpace.put ( "ad " , 0.22  );
		_trigramsIncludingSpace.put ( "as " , 0.41  );
		_trigramsIncludingSpace.put ( " i " , 0.28  );
		_trigramsIncludingSpace.put ( "d h" , 0.22  );
		_trigramsIncludingSpace.put ( "her" , 0.41  );
		_trigramsIncludingSpace.put ( "ly " , 0.28  );
		_trigramsIncludingSpace.put ( "e o" , 0.22  );
		
		_trigramsIncludingSpace.put ( "in " , 0.39  );
		_trigramsIncludingSpace.put ( "s a" , 0.27  );
		_trigramsIncludingSpace.put ( "ve " , 0.22  );

	}
	
	
	public static Frequency getInstance()
	{
		if(_instance==null)
		{
			_instance=new Frequency();
		}
		return _instance;
	}


	
	public HashMap < Integer , Double > get_wordLengths ( )
	{
		return _wordLengths;
	}


	
	public void set_wordLengths ( HashMap < Integer , Double > lengths )
	{
		_wordLengths = lengths;
	}


	
	public HashMap < Character , Double > get_monogramFrequencies ( )
	{
		return _monogramFrequencies;
	}


	
	public void set_monogramFrequencies ( HashMap < Character , Double > frequencies )
	{
		_monogramFrequencies = frequencies;
	}


	
	public HashMap < String , Double > get_bigramsIncludingSpace ( )
	{
		return _bigramsIncludingSpace;
	}


	
	public void set_bigramsIncludingSpace ( HashMap < String , Double > includingSpace )
	{
		_bigramsIncludingSpace = includingSpace;
	}


	
	public HashMap < String , Double > get_trigramsIncludingSpace ( )
	{
		return _trigramsIncludingSpace;
	}


	
	public void set_trigramsIncludingSpace ( HashMap < String , Double > includingSpace )
	{
		_trigramsIncludingSpace = includingSpace;
	}
	
	
}
