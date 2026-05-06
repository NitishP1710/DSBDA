import org.apache.spark.sql.SparkSession
object WordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Simple Word Count")
      .master("local[*]") // Uses all available cores on your machine
      .getOrCreate()
    val data = Seq("Spark is fast", "Spark is fun", "Scala is powerful")
    val rdd = spark.sparkContext.parallelize(data)

    val counts = rdd
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.collect().foreach(println)

    spark.stop()
  }
}
