package com.paperless.services.mapper;

import com.paperless.persistence.entities.AuthPermission;
import com.paperless.persistence.entities.AuthUser;
import com.paperless.persistence.repositories.*;
import com.paperless.services.dto.gets.GetDocument200ResponsePermissions;
import com.paperless.services.dto.gets.GetDocument200ResponsePermissionsView;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PermissionsMapper implements EntityMapper<GetDocument200ResponsePermissions, AuthUser> {

    @Autowired
    private DocumentsCorrespondentRepository correspondentRepository;
    @Autowired
    private DocumentsDocumenttypeRepository documentTypeRepository;
    @Autowired
    private DocumentsStoragepathRepository storagePathRepository;
    @Autowired
    private AuthUserRepository userRepository;
    @Autowired
    private AuthGroupRepository groupRepository;
    @Autowired
    private DocumentsDocumentTagsRepository documentTagsRepository;
    @Autowired
    private AuthPermissionRepository  permissionRepository;

    @Mapping(target = "view", source = "id", qualifiedByName = "viewEntity")
    @Mapping(target = "change", source = "id", qualifiedByName = "changeEntity")
    abstract public GetDocument200ResponsePermissions toDto(AuthPermission entity);

    @Named("viewEntity")
    GetDocument200ResponsePermissionsView map1(Integer id) {
        if(id==null)
            return new GetDocument200ResponsePermissionsView();
        return new GetDocument200ResponsePermissionsView().addUsersItem(id);
    }

    @Named("changeEntity")
    GetDocument200ResponsePermissionsView map2(Integer id) {
        if(id==null)
            return new GetDocument200ResponsePermissionsView();
        return new GetDocument200ResponsePermissionsView().addUsersItem(id);
    }

}
