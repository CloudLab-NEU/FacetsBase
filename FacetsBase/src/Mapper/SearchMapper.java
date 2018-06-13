package Mapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import util.Utilities;

public class SearchMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		// Load Condition
        Configuration conf = context.getConfiguration();
        Map<String, List<Integer>> facetMap = null;
        List<Integer> arr = null;
        Boolean flag = false;
        facetMap = Utilities.strToMap(conf.get("ids"));
        
        LongWritable l = new LongWritable();
        Text t = new Text();
        
        if(facetMap==null||facetMap.isEmpty()) {
        	l.set((long)222);
        	t.set("hello");
        	context.write(l, t);
        	return;
        }
        	
        LongWritable id = new LongWritable();
        Text value = new Text();
        
        InputSplit inputSplit = context.getInputSplit();
        String str = ((FileSplit) inputSplit).getPath().toString();
        str = str.replaceAll("hdfs://Master:9000", "");
        str = str.replaceAll("_facet.txt", "");
        str = str.replaceAll("_trival.txt", "");
//        if(facetMap.containsKey(str))
//        	arr = facetMap.get(str);
//        else
//        	return;
        Iterator<Map.Entry<String, List<Integer>>> entries = facetMap.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, List<Integer>> entry = entries.next();  
		  
			String filename = entry.getKey();
			
			if(filename.contains(str)) {
				flag = true;
				arr = entry.getValue();
				break;
			}
		}
		if(flag == false) {
			l.set((long)222);
        	t.set(str);
        	context.write(l, t);
        	return;
		}
			
        
        String _value = ivalue.toString();
        String[] idAndValue = _value.split("\\|", 2);
        Integer _id = Integer.valueOf(idAndValue[0]);
        
        
        if(Utilities.isValueInArr(_id, arr)) {
        	id.set(_id);
        	value.set(idAndValue[1]);
        	
        	context.write(id, value);
        }
        else {
        	l.set((long)222);
        	t.set("hellohi");
        	context.write(l, t);
        }
	}

}
