# CSV to MDB converter

This script can be used to convert all cvs files in a folder to a single mdb database. Usefull for creating fast testing database assets.

We just used it one time, so the whole project is not in a maven/ant/gradle build script but in a plain ecplise project. If I find some time I would like to correct that.

## How to use

`java -jar CSVToMDB.jar <folder_dir> <mdb_export_dir>`

## Dependencys

This Application uses Jackcess (http://jackcess.sourceforge.net/) for writing the database.
