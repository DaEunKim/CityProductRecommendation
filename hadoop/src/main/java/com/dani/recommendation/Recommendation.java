package com.dani.recommendation;

/**
 * packageName    : com.dani.recommendation
 * fileName       : Recommendation
 * author         : kim-daeun
 * date           : 2024/06/15
 * description    :
 */
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Recommendation {

    public static class TokenizerMapper extends Mapper<Object, Text, IntWritable, Text> {

        private IntWritable userId = new IntWritable();
        private Text itemCategory = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] parts = value.toString().split("\t");
            if (parts.length == 7) {
                userId.set(Integer.parseInt(parts[0]));
                itemCategory.set(parts[3] + "," + parts[4] + "," + parts[6]);
                context.write(userId, itemCategory);
            }
        }
    }

    public static class IntSumReducer extends Reducer<IntWritable, Text, Text, IntWritable> {

        private IntWritable result = new IntWritable();
        private Text itemCategory = new Text();

        public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                String[] parts = val.toString().split(",");
                itemCategory.set(parts[1] + "," + parts[2]);  // category,title
                result.set(Integer.parseInt(parts[0]));
                context.write(itemCategory, result);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "item recommendation");
        job.setJarByClass(Recommendation.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
