package run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import Mapper.SearchMapper;
import Reducer.SearchReducer;
import impl.WriteAndReadSearchFileNameAndIds;
import util.Utilities;

public class JoinSearch {
	
	private static final String resDilename = "hdfs://192.168.73.134:9000/res/FileName";
	private static Map<String, List<Integer>> facetMap = null;;
	private static String out = "hdfs://192.168.73.134:9000/JoinSearchOutput";
	private static String fileNames = "";

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		WriteAndReadSearchFileNameAndIds writeAndReadSearchFileNameAndIds = new WriteAndReadSearchFileNameAndIds();
		String str = writeAndReadSearchFileNameAndIds.read(resDilename);
		facetMap = Utilities.strToMap(str);
		
		if(facetMap==null||facetMap.isEmpty()) {
			System.out.println("查询结果为空！");
		}
		
		System.out.println("map:" + facetMap.size());
		
		Iterator<Map.Entry<String, List<Integer>>> entries = facetMap.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, List<Integer>> entry = entries.next();  
		  
			String filename = entry.getKey();
		    List<Integer> list = entry.getValue();
		    
		    System.out.println("list:" + list.size());
		    
		    if(list.isEmpty()||list==null) {
//		    	fileNames += filename + ",";
		    }
		    else {
		    	fileNames += filename.replaceAll(".txt", "_facet.txt") + ",";
		    	fileNames += filename.replaceAll(".txt", "_trival.txt") + ",";
		    }
		}
		int len = fileNames.length();
		fileNames = fileNames.substring(0, 0+len-1);
		System.out.println("fileNames:" + fileNames);
		
		
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		
		// 为conf添加查询参数
		conf.set("ids", Utilities.mapToStr(facetMap));
		
		// 设置jar包及作业名称
		@SuppressWarnings("deprecation")
		Job job = new Job(conf, "JoinSearch");
		job.setJarByClass(JoinSearch.class);
		
		job.setMapperClass(SearchMapper.class);
        job.setReducerClass(SearchReducer.class);
        
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);
        
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        
        // 输入输出路径
        FileInputFormat.addInputPaths(job, fileNames);
//     	FileInputFormat.addInputPath(job, new Path(in1));
//     	FileInputFormat.addInputPath(job, new Path(in2));
     	FileOutputFormat.setOutputPath(job, new Path(out));
	    
//     	job.waitForCompletion(true);
     	
     	long joinsearchstarttime = System.currentTimeMillis();
	    System.exit(job.waitForCompletion(true)?0:1);
	    long joinsearchendtime = System.currentTimeMillis();
	    System.out.println("JoinSearch总共用时"+  (joinsearchendtime-joinsearchstarttime) +"ms");
	    
	}

}
