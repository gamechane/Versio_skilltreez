package com.gamechane.backend.skilltree.service;

import Utils.Utils;
import com.gamechane.backend.exceptions.InvalidDataException;
import com.gamechane.backend.skilltree.domain.Connection;
import com.gamechane.backend.skilltree.domain.SkillTreeGroup;
import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.skilltree.domain.SkillTree;
import com.gamechane.backend.skilltree.domain.GameBlock;
import com.gamechane.backend.skilltree.persistence.ConnectionRepository;
import com.gamechane.backend.skilltree.persistence.GroupRepository;
import com.gamechane.backend.skilltree.persistence.SkillTreeRepository;
import com.gamechane.backend.skilltree.persistence.GameBlockRepository;
import com.gamechane.backend.skilltree.presentation.DTO.ConnectionDTO;
import com.gamechane.backend.skilltree.presentation.DTO.GroupDTO;
import com.gamechane.backend.skilltree.presentation.DTO.SkillTreeDTO;
import com.gamechane.backend.skilltree.presentation.DTO.GameBlockDTO;
import com.gamechane.backend.game.domain.Game;
import com.gamechane.backend.game.persistence.GameRepository;
import com.gamechane.backend.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SkillTreeService {
    private final SkillTreeRepository skillTreeRepository;
    private final GroupRepository groupRepository;
    private final GameBlockRepository gameBlockRepository;
    private final GameRepository gameRepository;
    private final ConnectionRepository connectionRepository;

    public SkillTreeService(SkillTreeRepository skillTreeRepository,
                            GroupRepository groupRepository,
                            GameBlockRepository gameBlockRepository,
                            GameRepository gameRepository,
                            ConnectionRepository connectionRepository) {
        this.skillTreeRepository = skillTreeRepository;
        this.groupRepository = groupRepository;
        this.gameBlockRepository = gameBlockRepository;
        this.gameRepository = gameRepository;
        this.connectionRepository = connectionRepository;
    }

    public List<SkillTreeDTO> findSkillTreeByUsername(String user) {
        List<SkillTree> skillTrees = skillTreeRepository.findByUsername(user);
        List<SkillTreeDTO> skillTreeDTOS = new ArrayList<>();

        // Loop door de gevonden skilltrees heen en koppel de bijbehorende objecten.
        for (SkillTree skillTree : skillTrees) {
            skillTreeDTOS.add(ObjectMapperUtils.map(skillTree, SkillTreeDTO.class));
        }
        return skillTreeDTOS;
    }

    public Boolean gameExistsInSkillTree(String user, Long gameId) {
        List<SkillTree> skillTrees = skillTreeRepository.findByUsername(user);
        boolean gameExists = false;

        for (SkillTree skillTree : skillTrees){
            GameBlock block = skillTree.getGameBlocks().stream().filter(b -> b.getGame().getId().equals(gameId)).findFirst().orElse(null);

            if (block != null) gameExists = true;
        }

        return gameExists;
    }

    public SkillTreeDTO findSkillTreeById(Long skillTreeId) throws Exception {
        Optional<SkillTree> skillTree = skillTreeRepository.findById(skillTreeId);
        if (skillTree.isEmpty()) {
            throw new NoRecordsFoundException(String.format("No skill tree found with id: '%d'.", skillTreeId));
        }
        return ObjectMapperUtils.map(skillTree.get(), SkillTreeDTO.class);
    }

    public SkillTreeDTO createSkillTree(String username, SkillTreeDTO skillTreeDTO) {
        try {
            SkillTree skillTree = ObjectMapperUtils.map(skillTreeDTO, SkillTree.class);

            skillTree.setUsername(username);

            skillTree.setGameBlocks(new ArrayList<>());
            for(GameBlockDTO block: skillTreeDTO.getGameBlocks()){
                GameBlock newGameBlock = new GameBlock();
                newGameBlock.setName(block.getName());
                newGameBlock.setRequiredPoints(block.getRequiredPoints());
                newGameBlock.setValueInPoints(block.getValueInPoints());
                newGameBlock.setMystery(block.isMystery());
                newGameBlock.setBlockId(block.getBlockId());

                Game game = null;

                if (block.getGameId() != null) game = gameRepository.findById(block.getGameId()).orElseThrow();
                newGameBlock.setGame(game);

                List<Connection> connections = new ArrayList<>();
                if(block.getConnections().size() != 0){
                    for(ConnectionDTO dto: block.getConnections()){
                        connections.add(ObjectMapperUtils.map(dto, Connection.class));
//                        Connection connection = new Connection();
//                        connection.setDestination(dto.getDestination());
//                        connection.setStartPoint(dto.getStartPoint());
//                        connection.setEndPoint(dto.getEndPoint());
//
//                        newSpelBlock.addConnection(connection);
                    }
                }

                newGameBlock.setConnections(connections);

                skillTree.addGameBlock(newGameBlock);
            }

            skillTree.setGroups(new ArrayList<>());
            for(GroupDTO groupDTO: skillTreeDTO.getGroups()){
                SkillTreeGroup group = new SkillTreeGroup();
                group.setName(groupDTO.getName());
                group.setGamePin(groupDTO.getGamePin());
                group.setSkillTree(skillTree);
                skillTree.addGroup(group);
            }

            skillTree = skillTreeRepository.save(skillTree);

            return ObjectMapperUtils.map(skillTree, SkillTreeDTO.class);
        } catch(Exception e) {
            Utils.writeErrorLog(skillTreeDTO.toString() + e);
            throw(e);
        }
    }

    /*
    Bewerk een skilltree van een gebruiker via een ArrangementDTO.
    */
    public SkillTreeDTO updateSkillTree(String username, SkillTreeDTO skillTreeDTO) throws Exception {
        try {
            Optional<SkillTree> storedSkillTree = skillTreeRepository.findByIdAndUsername(skillTreeDTO.getId(), username);

            if (!storedSkillTree.isPresent()) {
                throw new NoRecordsFoundException("Cannot find skill tree.");
            }

            SkillTree skillTree = ObjectMapperUtils.map(skillTreeDTO, SkillTree.class);

            skillTree.setUsername(username);

            skillTree.setGameBlocks(new ArrayList<>());
            for(GameBlockDTO block: skillTreeDTO.getGameBlocks()){
                GameBlock newGameBlock = new GameBlock();
                newGameBlock.setId(block.getId());
                newGameBlock.setName(block.getName());
                newGameBlock.setRequiredPoints(block.getRequiredPoints());
                newGameBlock.setValueInPoints(block.getValueInPoints());
                newGameBlock.setMystery(block.isMystery());
                newGameBlock.setBlockId(block.getBlockId());

                Game game = null;

                if (block.getGameId() != null) game = gameRepository.findById(block.getGameId()).orElseThrow();
                newGameBlock.setGame(game);

                List<Connection> connections = new ArrayList<>();

                if (block.getConnections().size() != 0){
                    for(ConnectionDTO dto: block.getConnections()){
                        connections.add(ObjectMapperUtils.map(dto, Connection.class));
//                        Connection connection = new Connection();
//                        connection.setDestination(dto.getDestination());
//                        connection.setStartPoint(dto.getStartPoint());
//                        connection.setEndPoint(dto.getEndPoint());
//
//                        newSpelBlock.addConnection(connection);
                    }
                }

                newGameBlock.setConnections(connections);
                skillTree.addGameBlock(newGameBlock);
            }

            skillTree.setGroups(new ArrayList<>());

            for (GroupDTO groupDTO: skillTreeDTO.getGroups()) {
                SkillTreeGroup group = new SkillTreeGroup();
                group.setName(groupDTO.getName());
                group.setGamePin(groupDTO.getGamePin());
                group.setSkillTree(skillTree);
                skillTree.addGroup(group);
            }

            // Tijdelijke fix voor probleem - unique constraint violation
            this.deleteSkillTreeGroups(skillTree);

            SkillTree dbSkillTree = storedSkillTree.get();
            dbSkillTree.setId(skillTree.getId());
            dbSkillTree.setName(skillTree.getName());
            dbSkillTree.setPoints(skillTree.getPoints());
            dbSkillTree.setUsername(skillTree.getUsername());
            dbSkillTree.setFinalMessage(skillTree.getFinalMessage());
            dbSkillTree.setIsPublic(skillTree.getIsPublic());
            dbSkillTree.setGameBlocks(skillTree.getGameBlocks());
            dbSkillTree.setGroups(skillTree.getGroups());
            skillTreeRepository.save(dbSkillTree);

            return ObjectMapperUtils.map(dbSkillTree, SkillTreeDTO.class);
        }catch(Exception e){
            Utils.writeErrorLog(skillTreeDTO.toString() + e);
            throw(e);
        }

//        skilltreeDTO.setGebruiker(gebruiker);
//        Optional<Skilltree> skilltreeOptional = skilltreeRepository.findByIdAndGebruiker(skilltreeDTO.getId(), gebruiker);
//        if (!skilltreeOptional.isPresent()) {
//            throw new NoRecordsFoundException("Kan skilltree niet vinden");
//        }
//        Skilltree skilltree = skilltreeOptional.get();
//
//        skilltree.setId(skilltreeDTO.getId());
//        skilltree.setEindbericht(skilltreeDTO.getEindbericht());
//        skilltree.setNaam(skilltreeDTO.getNaam());
//        skilltree.setOmschrijving(skilltreeDTO.getOmschrijving());
//        skilltree.setOpenbaar(skilltreeDTO.getOpenbaar());
//        skilltree.setSpelBlokken(new ArrayList<>());
//        skilltree.setGroepen(new ArrayList<>());
//
//        for (SpelBlokDTO blok: skilltreeDTO.getSpelBlokken()) {
//            skilltree.addSpelBlok(ObjectMapperUtils.map(blok, SpelBlok.class));
//        }
//
//        log.info(skilltreeDTO.getGroepen().toString());
//
//        for (GroepDTO groep : skilltreeDTO.getGroepen()){
//            groep.setSkilltree_id(skilltreeDTO.getId());
//            SkilltreeGroep stGroep = ObjectMapperUtils.map(groep, SkilltreeGroep.class);
//            stGroep.setSkilltree(skilltree);
//            skilltree.addGroep(stGroep);
//            log.info(stGroep.toString());
//        }
//
//        skilltree.getGroepen().forEach(System.out::println);
//        skilltreeRepository.save(skilltree);
//
//        return ObjectMapperUtils.map(skilltree, SkilltreeDTO.class);
    }

    public Boolean deleteSkillTree(String username, Long id) throws Exception {
        // Controleer of er een skill tree bestaat met meegegeven gebruiker en id.
        Optional<SkillTree> skillTreeOptional = skillTreeRepository.findByIdAndUsername(id, username);

        if (skillTreeOptional.isEmpty()) {
            throw new NoRecordsFoundException("No skill tree found.");
        }

        skillTreeOptional = skillTreeRepository.findById(id);

        if (skillTreeOptional.isPresent()) {
            SkillTree skillTree = skillTreeOptional.get();

            // Verwijder de gekoppelde objecten.
            gameBlockRepository.deleteAll(skillTree.getGameBlocks());
            groupRepository.deleteAll(skillTree.getGroups());
            skillTreeRepository.delete(skillTree);
        }

        return true;
    }

    /*
    Haal een skilltree op met behulp van een gamePin.
     */
    public SkillTreeDTO loginWithGamePin(String gamePin) throws Exception {
        Optional<SkillTreeGroup> group = groupRepository.findByGamePin(gamePin);

        if (group.isEmpty()) {
            throw new InvalidDataException("Ongeldige gamepin");
        }

        SkillTree skillTree = group.get().getSkillTree();

        return ObjectMapperUtils.map(skillTree, SkillTreeDTO.class);
    }

    // TODO
    // Betere fix vinden dan dit
    private void deleteSkillTreeGroups (SkillTree skillTree) {
        for(SkillTreeGroup group : skillTree.getGroups()) {
            Optional<SkillTreeGroup> skilltreeGroep = groupRepository.findByGamePin(group.getGamePin());
            skilltreeGroep.ifPresent(groupRepository::delete);
        }
    }
}


