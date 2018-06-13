package Reducer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SearchReducer extends Reducer<LongWritable, Text, Text, Text> {

	public void reduce(LongWritable _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		Text id = new Text();
		Text value = new Text();
		
		String _str = "";
		
		// process values
//		for (Text val : values) {
//			String _str = new String(val.toString());
//			if(str.length()<_str.length()) {
//				str = str + "----------" + _str;
//			}
//			else {
//				str = _str + "----------" + str;
//			}
//		}
		
		ArrayList<String> str = new ArrayList<String>();
		
		for (Text val : values) {
			str.add(val.toString());
		}
		
		if(str.size()==2) {
			if(str.get(0).length()>=str.get(1).length()) {
				_str = str.get(1) + "--------" + str.get(0);
			}
			else {
				_str = str.get(0) + "--------" + str.get(1);
			}
			id.set(String.valueOf(_key));
			value.set(_str);
			
			context.write(id, value);
		}
	}

}
