package lab23.service;

public class MemberPageGenerationService {

  private MemberPageGenerationService() {}

  private static MemberPageGenerationService instance;

  public static MemberPageGenerationService getInstance() {
    if (instance == null) {
      instance = new MemberPageGenerationService();
    }
    return instance;
  }

  public String getPage(String memberId) {
    String memberName = "Member: " + getMembersName(memberId);
    return String.format("""
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="member.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MAY Team</title>
<link rel="icon" href=
"./img/MAY-icon-without-bg.png"
          type="image/x-icon">
</head>
<body>
	<div class="img">
		<img src="./img/noface.png">
	</div>
	
	<div class="info">
		<p>%s</p>
		<p>Occupation: Student</p>
		<p>University: National Technical University of Ukraine "Igor Sikorsky Kyiv Polytechnic Institute".</p>
		<p>Faculty: Faculty of Informatics and Computer Engineering</p>
		<p>Group: IA-33</p>
	</div>
</body>
</html>
""", memberName);
  }
  
  private static String getMembersName(String memberId) {
	  if (memberId.equals("1")){
		  return "Zaranik Maksym";
	  }else if(memberId.equals("2")) {
		  return "Hrytsai Andrii";
	  }else {
		  return "Yakymenko Yaroslav";
	  }
  }

}