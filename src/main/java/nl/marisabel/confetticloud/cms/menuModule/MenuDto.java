package nl.marisabel.confetticloud.cms.menuModule;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "Menu DTO")
public class MenuDto {
 private Long id;
 private String name;
 private String url;
 private int displayOrder;
 private boolean showInMenu;
}
