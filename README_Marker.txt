USAGE:

To encode: cat file | java LZencode > output
To encode and pack: cat file | java LZencode | java LZpack > output
To decode: cat file | java LZdecode > output
To unpack and decode: cat file | java LZunpack | java LZdecode > output

Please note that if there is no mismatch char on the final line, a null char will be used
and this will result in a null char at the end of the decoded output file.

Have a nice day :)