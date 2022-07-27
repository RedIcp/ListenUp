package com.listenup.individualassignment.business.user.account.imp;

import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.business.user.account.DeleteAccountUseCase;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class DeleteAccountUseCaseImp implements DeleteAccountUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;

    @Override
    public boolean deleteAccount(long id){
        boolean result = false;

        authorised.isAuthorised(id);

        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }

        return result;
    }
}
