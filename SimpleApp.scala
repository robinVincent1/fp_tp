import org.apache.spark.sql.SparkSession

  object SimpleApp {
    def main(args: Array[String]): Unit = {
      val logFile = "Data/Readme.md" // Should be some file on your system
      val spark = SparkSession.builder
        .appName("Simple Application")
        .master("local[*]")
        .getOrCreate()
      spark.sparkContext.setLogLevel("ERROR")
      val logData = spark.read.textFile(logFile).cache()
      val numAs = logData.filter(line => line.contains("a")).count()
      val numBs = logData.filter(line => line.contains("b")).count()
      println(s"Lines with a: $numAs, Lines with b: $numBs")
      spark.stop()
    }
}
