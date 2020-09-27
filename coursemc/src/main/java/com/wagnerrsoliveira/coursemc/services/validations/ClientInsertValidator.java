package com.wagnerrsoliveira.coursemc.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wagnerrsoliveira.coursemc.domain.enums.ClientType;
import com.wagnerrsoliveira.coursemc.dtos.ClientNewDTO;
import com.wagnerrsoliveira.coursemc.resources.exceptions.FieldMessage;
import com.wagnerrsoliveira.coursemc.services.validations.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Override
	public void initialize(ClientInsert clientInsert) {
	}

	@Override
	public boolean isValid(ClientNewDTO clientNewDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(clientNewDTO.getType().equals(ClientType.INDIVIDUAL.getId()) && !BR.isValidSsn(clientNewDTO.getRegistryId())) {
			list.add(new FieldMessage("registryId","Registry Id invalid"));
		}
		
		if(clientNewDTO.getType().equals(ClientType.LEGALENTITY.getId()) && !BR.isValidTfn(clientNewDTO.getRegistryId())) {
			list.add(new FieldMessage("registryId","Registry Id invalid"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
