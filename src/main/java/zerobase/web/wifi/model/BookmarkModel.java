package zerobase.web.wifi.model;
import lombok.*;
import javax.servlet.http.HttpServletRequest;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BookmarkModel {
    int id;
    String mgrNo;
    int bookMarkGroupId;
    public static BookmarkModel getParameter(HttpServletRequest request) {
        int id = 0;
        int bookMarkGroupId = 0;
        String mgrNo = request.getParameter("mgrNo");
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {}
        try {
            bookMarkGroupId = Integer.parseInt(request.getParameter("bookMarkGroupId"));
        } catch (Exception e) {}
        return BookmarkModel.builder()
                .id(id)
                .mgrNo(mgrNo)
                .bookMarkGroupId(bookMarkGroupId)
                .build();}}