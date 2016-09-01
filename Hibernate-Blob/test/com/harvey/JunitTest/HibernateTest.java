package com.harvey.JunitTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.harvey.Entity.Student;
import com.harvey.Util.HibernateUtil;
import com.harvey.Util.NoEntityException;


/**
 * This program demonstrates how to save and get Blob from Database in Hibernate
 * @database:mysql 5.6 
 * @hibernate:4.3.6
 * @author Harvey
 * @date Sep 1, 2016
 * @time 4:49:30 PM
 */
public class HibernateTest {
	
	@Test
	public void saveBlob(){
		//hibernate session and transaction
		Session session=null;
		Transaction tx=null;
		//entity
		Student student = null;
		Blob blob = null;
		//file and stream
		File file = null;
		FileInputStream inputStream = null;
		
		//get file path for Blob
		String path= "E:/1.pdf";
		try {
			//1.get session and begin transaction
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			//2.create entity
			student = new Student();
			student.setName("bigObject");
			
			//3.get blob
			file = new File(path);
			inputStream = new FileInputStream(file);
			blob = Hibernate.getLobCreator(session)
                    .createBlob(inputStream, file.length());
			//4.save blob
	        student.setImage(blob);
			session.save(student);
			
			//5.free resource and commit
			blob.free();
			tx.commit();
			System.out.print("save success");
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
		} finally{
			//close the stream
			try {
				if(inputStream!=null){
					inputStream.close();
					inputStream = null;
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}	
			HibernateUtil.closeSession();
		}
	}
	
	@Test
	public void loadBlob(){
		//Hibernate session
		Session session=null;
		Transaction tx=null;
		//Entity
		Blob blob = null;
		Student student = null;
		String path = "E:/3.ppt";
		try{
			//1.get session
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//2.get blob from a student
			student = (Student) session.get(Student.class, 1); 
			if(student == null){
				throw new NoEntityException("No Entity Exception, please check the id of the entity");
			}
			blob = student.getImage();
			
			//3.save it to a file
			saveBytesToFile(path, blob);
			
			//4.free blob and commit
			blob.free();
			tx.commit();
			System.out.print("load success");
        }
		catch(NoEntityException e){
			 e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			HibernateUtil.closeSession();
		}
		
	}
	
	
	/**
	 * save image(Blob) file to filePath 
	 * @param filePath
	 * @param blob
	 */
	private static void saveBytesToFile(String filePath, Blob blob) {
		byte[] blobBytes;
		FileOutputStream outputStream = null;
		try {
			//transfer blob into bytes
			blobBytes = blob.getBytes(1, (int) blob.length());
			
			outputStream = new FileOutputStream(filePath);
	        outputStream.write(blobBytes);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//close output-stream
			try {
				if(outputStream != null){
					outputStream.close();
					outputStream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
        
    }
}

