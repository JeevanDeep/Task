package jeevan.com.task.utils

import android.os.Environment
import jeevan.com.task.model.ContactModel
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class CsvWriter {

    companion object {
        fun saveToCsv(fileName: String, list: ArrayList<ContactModel>) {

            val fileWriter = FileWriter(fileName)
            val FILE_HEADER = "name,phoneNumber"
            val COMMA_DELIMITER = ","
            val NEW_LINE_SEPARATOR = "\n"

            fileWriter.append(FILE_HEADER)
            fileWriter.append(NEW_LINE_SEPARATOR)

            for (i in list.indices) {
                fileWriter.append(list[i].name)
                fileWriter.append(COMMA_DELIMITER)
                fileWriter.append(list[i].phoneNumber)
                fileWriter.append(NEW_LINE_SEPARATOR)
            }

            println("done")


            fileWriter.flush()
            fileWriter.close()

            saveToZip(arrayOf(fileName))


        }

        private fun saveToZip(files: Array<String>) {
            ZipOutputStream(BufferedOutputStream(FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/MyFolder/Sample.zip"))).use { out ->
                val data = ByteArray(1024)
                for (file in files) {
                    FileInputStream(file).use { fi ->
                        BufferedInputStream(fi).use { origin ->
                            val entry = ZipEntry(file)
                            out.putNextEntry(entry)
                            while (true) {
                                val readBytes = origin.read(data)
                                if (readBytes == -1) {
                                    break
                                }
                                out.write(data, 0, readBytes)
                            }
                        }
                    }
                }

            }
        }


    }
}

