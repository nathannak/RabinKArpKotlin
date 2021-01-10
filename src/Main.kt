
fun main(args: Array<String>){
    println(rabinKarp())
}

fun rabinKarp():Int{

    //example text and pattern

    //set1
//    val text = "needleinhaystack"
//    val pattern = "inhay"

    //set2
    val text = "needleinhaystack"
    val pattern = "need"

    //set3
//    val text = "needleinhaystack"
//    val pattern = "tack"

     return rabinKarpHelper(text.toCharArray(),pattern.toCharArray())
}

private fun rabinKarpHelper(text: CharArray , pattern: CharArray):Int {

    //length of text and pattern
    val N = text.size
    val M = pattern.size

    val prime = 101
    var powM  = 1

    var textHash = 0
    var patternHash = 0

    //corner case
    if(M==0 || M>N) return -1

    //DEVELOP your powM
    for(i in 0 until M-1){ // M-1 times
        powM = (powM shl 1) % prime
    }

    //Get initial PATTERN and TEXT hash
    for(i in 0 until M){ // M times
        patternHash =  ((patternHash shl 1 ) + (pattern[i].toInt()))%prime
        textHash    =  ((textHash shl 1) + (text[i].toInt()))%prime
    }

    //Find MATCH
    for(i in 0..N-M) { // N-M inclusive

        //equal hash, but do we have equal String?

        if(textHash == patternHash){
            loop@for(j in 0 until M){
                if(text[i+j]!=pattern[j]) break@loop

                if(j==M-1) // if j hits last index, we have full match
                    return i
            }
        }

        if(i<N-M) { // i+M will cause indexOutOfBounds exception
            textHash = ( ((textHash - (text[i].toInt()) * powM) shl 1) + (text[i + M].toInt()) ) % prime
            if (textHash < 0)
                textHash += prime
        }

    }

    return -1
}