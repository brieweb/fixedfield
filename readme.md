FixedField parser language

Brian E. Lavender

FixedField.g4

This is the fixed field format parser specification language. It is
inspired by the file portion in COBOL. The fact that Java doesn't have
good fixed field parsing and seems to just offer substring where it is
easy to overlap one call over another. The file section is described on
the following website:

http://people.sju.edu/~jhodgson/cobol/data.html

If you look at the sample format.txt, you will see the layout for the
fixed format specification.  The user can specify the field width by
repeating the format character n times. Or, the user can specify the
format character with a multiplier in parenthesis. The benefit of the
parsing language is that arguments to substring are calculated and it
easy to verify there will be no cross over.

To compile:

$ mvn compile

In order to run, you need to provide two arguments. The first is the
file that has the parse format. The second is the file being parsed. Both
files are provided.

mvn exec:java -Dexec.mainClass="test.E1" -Dexec.args="format.txt sample.dat"

Output:
The output shows the result of parsing the sample.dat file based upon
the format.txt parsing specification.

Some limitations:
The program uses the format to produce the start and stop values for
substring in Java. If the input doesn't have enough characters, the error
message just throws the exception rather than showing that there was not
enough input for the input line. It also does not check the input that
it matches the format type. It will also just print out a message if you
specify do not specify "9" for number format, but not stop evaluation. I
had to tell the grammar that this was a digit and not just a "9".

