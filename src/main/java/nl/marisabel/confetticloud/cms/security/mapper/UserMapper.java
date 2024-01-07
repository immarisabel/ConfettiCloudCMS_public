package nl.marisabel.confetticloud.cms.security.mapper;

import nl.marisabel.confetticloud.cms.security.user.model.UserModel;
import nl.marisabel.confetticloud.cms.security.dto.AuthenticatedUserDto;
import nl.marisabel.confetticloud.cms.security.dto.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserModel convertToUser(RegistrationRequest registrationRequest);

	AuthenticatedUserDto convertToAuthenticatedUserDto(UserModel userModel);

	UserModel convertToUser(AuthenticatedUserDto authenticatedUserDto);

}
