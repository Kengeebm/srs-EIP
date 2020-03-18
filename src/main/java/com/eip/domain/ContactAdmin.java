package com.eip.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

import javax.mail.internet.InternetAddress;

@Document(collection = "request")
public class ContactAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("emp_id")
    private String empId;

    @Field("name")
    private String name;

    @Field("message")
    private String message;

    @Field("file_name")
    private String fileName;

    @Field("attachment")
    private String attachment;


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

   /* private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.write(attachment);
    }

    private void readObject(ObjectInputStream inputStream) throws IOException {
        inputStream.read(attachment);
    }*/


    @Override
    public String toString() {
        return "ContactAdmin{" +
            "empId='" + empId + '\'' +
            ", name='" + name + '\'' +
            ", message='" + message + '\'' +
            ", fileName=" + fileName +
            ", attachment=" + attachment +
            '}';
    }

	public InternetAddress getEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}
}
