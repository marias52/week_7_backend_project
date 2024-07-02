import java.lang.reflect.Member;

public class members {

//    properties
    private long membershipNo;
    private String memberName;
    private String startMembershipDate;


//constructor
    
    public members(long membershipNo, String memberName, String startMembershipDate) {
        this.membershipNo = membershipNo;
        this.memberName = memberName;
        this.startMembershipDate = startMembershipDate;
    }

// getters & setters
    public long getMembershipNo() {
        return membershipNo;
    }

    public void setMembershipNo(long membershipNo) {
        this.membershipNo = membershipNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStartMembershipDate() {
        return startMembershipDate;
    }

    public void setStartMembershipDate(String startMembershipDate) {
        this.startMembershipDate = startMembershipDate;
    }
}
