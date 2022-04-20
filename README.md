# LZ78

A program designed to compress files using the LZ78 method.

### Usage

To Compile: <br>
> javac *.java <br>

To Encode: <br>
> cat file | java LZencode > output

To Encode and Pack: <br>
> cat file | java LZencode | java LZpack > output

To Decode: <br>
> cat file | java LZdecode > output

To Unpack and Decode: <br>
> cat file | java LZunpack | java LZdecode > output

### Known Bugs
- Unpacker won't produce output if all the programs are piped together.
- A null char is used if there is no final mismatch char

Maybe I'll get round to fixing these one day...

Written in 2021, 15/15