// Member class

package library.objects;

import java.util.*;

public class Member {
    // Static member number
    private static int currentNum = 100;
    // Member number
    private final int memberNum;
    // ID
    private final int id;
    // Name
    private final String name;
    // Loans list
    private ArrayList<Loan> loans;

    // Constructor
    public Member(int id, String name) {
        this.memberNum = currentNum++;
        this.id = id;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    // Getters & Setters
    public int getMemberNum() {
        return memberNum;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    // Overload of Object's equals method with Member parameter
    public boolean equals(Member member) {
        if (member == null) return false;

        if (this == member) return true;

        return this.id == member.id &&
                this.name.equals(member.name);
    }

    // toString method
    @Override
    public String toString() {
        return "Member number " + memberNum +
                " - " + name +
                "      ID: " + id +
                "      Number of loans: " + loans.size();
    }

}
