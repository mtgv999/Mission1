package zerobase.web.wifi.model;
import lombok.*;
import javax.servlet.http.HttpServletRequest;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BookmarkGroupModel {
    int id;
    String name;
    int sortValue;
    public static BookmarkGroupModel getParameter(HttpServletRequest request) {
        int id = 0;
        String name = request.getParameter("name");
        int sortValue = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {}
        try {
            sortValue = Integer.parseInt(request.getParameter("sortValue"));
        } catch (Exception e) {}
        return BookmarkGroupModel.builder()
                .id(id)
                .name(name)
                .sortValue(sortValue)
                .build();}}