for f in *.csv
do
    filename=$(basename "$f")
    extension="${filename##*.}"
    filename="${filename%.*}"
    mongoimport --host 127.0.0.1:27017 -d Northwind -c "$filename" --type csv --file "$f" --headerline
done
