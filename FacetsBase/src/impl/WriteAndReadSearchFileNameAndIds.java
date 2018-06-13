package impl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import dao.WriteAndReadSearchFileNameAndIdsDao;

public class WriteAndReadSearchFileNameAndIds implements WriteAndReadSearchFileNameAndIdsDao {

	@SuppressWarnings("deprecation")
	@Override
	public void write(String filename, String context) {
		// TODO Auto-generated method stub
		Configuration conf = null;
		Path path = null;
		FileSystem fs = null;
		FSDataOutputStream out = null;
		
		try {
			conf = new Configuration();
			path = new Path(filename);
			fs = path.getFileSystem(conf);
			if(fs.exists(path)) {
				fs.delete(path);
			}
			out = fs.create(path);
			
			out.writeUTF(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String read(String filename) {
		// TODO Auto-generated method stub
		String str = "";
		Configuration conf = null;
		Path path = null;
		FileSystem fs = null;
		FSDataInputStream in = null;
		
		try {
			conf = new Configuration();
			path = new Path(filename);
			fs = path.getFileSystem(conf);
			if(!fs.exists(path)) {
				return "";
			}
			in = fs.open(path);
			
			str = in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return str;
	}

}
