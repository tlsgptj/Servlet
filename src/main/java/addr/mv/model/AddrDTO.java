package addr.mv.model;
import java.sql.Date;

public class AddrDTO {
	private long seq;
	private String name;
	private String addr;
	private Date rdate;
	
	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public AddrDTO(long seq, String name, String addr, Date rdate) {
		super();
		this.seq = seq;
		this.name = name;
		this.addr = addr;
		this.rdate = rdate;
	}
}
