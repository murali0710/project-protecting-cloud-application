package com.util;

import javax.crypto.SecretKey;

public class LogicBean {
private int bid;
private String uploadedby;
private String binarytype;
private String vmimage;
private String originalcode;
private String binarycode;
private String encrypted;
private String status;
private int uid;
private String search;
private String encryptKey;
private int pid;

public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getEncryptKey() {
	return encryptKey;
}
public void setEncryptKey(String secretKey) {
	this.encryptKey = secretKey;
}
public String getSearch() {
	return search;
}
public void setSearch(String search) {
	this.search = search;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public int getBid() {
	return bid;
}
public void setBid(int bid) {
	this.bid = bid;
}
public String getUploadedby() {
	return uploadedby;
}
public void setUploadedby(String uploadedby) {
	this.uploadedby = uploadedby;
}
public String getBinarytype() {
	return binarytype;
}
public void setBinarytype(String binarytype) {
	this.binarytype = binarytype;
}
public String getVmimage() {
	return vmimage;
}
public void setVmimage(String vmimage) {
	this.vmimage = vmimage;
}
public String getOriginalcode() {
	return originalcode;
}
public void setOriginalcode(String originalcode) {
	this.originalcode = originalcode;
}
public String getBinarycode() {
	return binarycode;
}
public void setBinarycode(String binarycode) {
	this.binarycode = binarycode;
}
public String getEncrypted() {
	return encrypted;
}
public void setEncrypted(String encrypted) {
	this.encrypted = encrypted;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
