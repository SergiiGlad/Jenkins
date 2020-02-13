import com.sun.Sun
def call(String str) {

    Sun example = new Sun( str )
    println("from sunClassLoad")
    example.print()
    return example
}