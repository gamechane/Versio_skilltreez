import { createRouter, createWebHistory } from "vue-router";
import LandingPage from "../views/LandingPage.vue";
import DashboardView from "../views/user/DashboardView.vue";
import RegistrationView from "../views/user/RegistrationView.vue";
import LoginView from "../views/user/LoginView.vue";
import LoginGamePinView from "../views/user/LoginGamePinView.vue";
import CreateGame from "@/views/game/CreateGame.vue";
import SkillTreeAanmakenView from "@/views/skilltree/SkillTreeAanmakenView.vue";
import SkillTreeOverzichtLeerlingView from "@/views/skilltree/SkillTreeOverzichtLeerlingView.vue";
import SkillTreeOverzichtDocentView from "@/views/skilltree/SkillTreeOverzichtDocentView.vue";
import ResultatenView from "@/views/user/ResultatenView.vue";
import ProfileDocentView from "@/views/user/profile/ProfileDocentView.vue";
import ProfileLeerlingView from "@/views/user/profile/ProfileLeerlingView.vue";
import SelectionGame from "@/views/game/selection/SelectionGame.vue";
import GameResultView from "@/views/game/GameResultView.vue";
import SkillTreeCompletedView from "@/views/skilltree/SkillTreeCompletedView.vue";
import CreateInformationCard from "@/views/game/information/CreateInformationCard.vue";
import {AuthenticationService} from "@/services/authentication.js";
import EditPasswordView from "@/views/user/profile/EditPasswordView.vue";
import MoreInformationView from "@/views/MoreInformationView.vue";
import CreateAnswerGame from "@/views/game/answer/CreateAnswerGame.vue";
import CreateRankingGame from "@/views/game/ranking/CreateRankingGame.vue";
import CreateRankingGameSettings from "@/views/game/ranking/CreateRankingGameSettings.vue";
import RankingGame from "@/views/game/ranking/RankingGame.vue";
import CreateClassifyGame from "@/views/game/classify/CreateClassifyGame.vue";
import CreateCombinationGame from "@/views/game/combination/CreateCombinationGame.vue";
import CreateCombinationGameSettings from "@/views/game//combination/CreateCombinationGameSettings.vue";
import CreateSelectionGame from "@/views/game/selection/CreateSelectionGame.vue";
import CreateSelectionGameSettings from "@/views/game/selection/CreateSelectionGameSettings.vue";
import CreateAnswerGameSettings from "@/views/game/answer/CreateAnswerGameSettings.vue";
import CombinationGame from "@/views/game/combination/CombinationGame.vue";
import AnswerGame from "@/views/game/answer/AnswerGame.vue";
import InformationCardPreview from "@/views/game/information/InformationCardPreview.vue";
import CreateInformationCardType from "@/views/game/information/CreateInformationCardType.vue";
import InformationCard from "@/views/game/information/InformationCard.vue";
import CreateClassifyGameSettings from "@/views/game/classify/CreateClassifyGameSettings.vue";
import GamesOverview from "@/views/game/GamesOverview.vue";
import ClassifyGame from "@/views/game/classify/ClassifyGame.vue";

const APP_NAME = "Skilltreez";
const APP_NAME_SUFFIX = " | ";


// See transfer document about authentication stuff
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Landing Page",
      component: LandingPage,
      meta: {
        auth: false,
        title: "Home" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/registration",
      name: "Registration",
      component: RegistrationView,
      meta: {
        auth: false,
        title: "Registration" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/login",
      name: "Login",
      component: LoginView,
      meta: {
        auth: false,
        title: "Login" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/login/game-pin",
      name: "Game pin login",
      component: LoginGamePinView,
      meta: {
        auth: false,
        title: "Game pin login" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/dashboard",
      name: "Dashboard",
      component: DashboardView,
      meta: {
        auth: true,
        title: "Dashboard" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/games",
      name: "Games",
      component: GamesOverview,
      meta: {
        auth: true,
        title: "Games overview" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/games/create",
      name: "Create game",
      component: CreateGame,
      meta: {
        auth: true,
        title: "Create game" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/games/create/classify-game",
      name: "Create classify game",
      component: CreateClassifyGame,
      meta: {
        auth: true,
        title: "Create classify game" + APP_NAME_SUFFIX + APP_NAME,
      }
    },
    {
      path: "/games/create/classify-game/settings",
      name: "Create classify game settings",
      component: CreateClassifyGameSettings,
      meta: {
        auth: true,
        title: "Create classify game settings" + APP_NAME_SUFFIX + APP_NAME,
      }
    },
    {
      path: "/games/classify-game/preview",
      name: "Classify game preview",
      component: ClassifyGame,
      meta: {
        auth: true,
        title: "Classify game preview" + APP_NAME_SUFFIX + APP_NAME,
      }
    },
    {
      path: "/games/create/combination-game",
      name: "Create combination game",
      component: CreateCombinationGame,
      meta: {
        auth: true,
        title: "Create combination game" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/create/combination-game/settings",
      name: "Create combination game settings",
      component: CreateCombinationGameSettings,
      meta: {
        auth: true,
        title: "Create combination game settings" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/combination-game/preview",
      name: "Combination game preview",
      component: CombinationGame,
      meta: {
        auth: true,
        title: "Combination game preview" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/create/selection-game",
      name: "Create selection game",
      component: CreateSelectionGame,
      meta: {
        auth: true,
        title: "View combination game settings" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/create/selection-game/settings",
      name: "Create selection game settings",
      component: CreateSelectionGameSettings,
      meta: {
        auth: true,
        title: "View combination game settings" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/selection-game/preview",
      name: "Selection game preview",
      component: SelectionGame,
      meta: {
        auth: true,
        title: "Selection game preview" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/create/answer-game",
      name: "Create answer game",
      component: CreateAnswerGame,
      meta: {
        auth: true,
        title: "Create answer game" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/create/answer-game/settings",
      name: "Create answer game settings",
      component: CreateAnswerGameSettings,
      meta: {
        auth: true,
        title: "Create answer game settings" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/games/answer-game/preview",
      name: "Answer game preview",
      component: AnswerGame,
      meta: {
        auth: true,
        title: "Answer game preview" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/create/ranking-game",
      name: "Create ranking game",
      component: CreateRankingGame,
      meta: {
        auth: true,
        title: "Create ranking game" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/create/ranking-game/settings",
      name: "Ranking game settings",
      component: CreateRankingGameSettings,
      meta: {
        auth: true,
        title: "Ranking game settings" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/games/ranking-game/preview",
      name: "Ranking game preview",
      component: RankingGame,
      meta: {
        auth: true,
        title: "Ranking game preview" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/games/create/information-card",
      name: "Create information card",
      component: CreateInformationCard,
      meta: {
        auth: true,
        title: "Create information card" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/games/create/information-card/:type",
      name: "Create information card with type",
      component: CreateInformationCardType,
      meta: {
        auth: true,
        title: "View combination game settings" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: '/games/information-card/preview/:type/:content',
      name: 'Information card preview',
      component: InformationCardPreview,
      meta: {
        auth: true,
        title: "Information card preview" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/games/information-card/:type",
      name: "Information card",
      component: InformationCard,
      meta: {
        auth: true,
        title: "Information card" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/game/:id(\\d+)/result", // Temporary path for developing, but should be shown after playing a game
      name: "Game result",
      component: GameResultView,
      meta: {
        auth: true,
        title: "Game result" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/create-skill-tree",
      name: "Create skill tree",
      component: SkillTreeAanmakenView,
      meta: {
        auth: true,
        title: "Create skill tree" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/skill-tree-overview-teacher",
      name: "Skill tree overview teacher",
      component: SkillTreeOverzichtDocentView,
      meta: {
        auth: true,
        title: "Skill tree overview teacher" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/skill-tree-overview-student",
      name: "Skill tree overview student",
      component: SkillTreeOverzichtLeerlingView,
      meta: {
        auth: true,
        title: "Skill tree overview student" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/skill-tree/:id(\\d+)/completed", // Temporary path for developing, but should be shown after completing a skilltree
      name: "Skill tree completed",
      component: SkillTreeCompletedView,
      meta: {
        auth: true,
        title: "Skill tree completed" + APP_NAME_SUFFIX + APP_NAME,
      },
    },
    {
      path: "/results",
      name: "Results",
      component: ResultatenView,
      meta: {
        auth: true,
        title: "Results" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/profile-teacher",
      name: "Profile teacher",
      component: ProfileDocentView,
      meta: {
        auth: true,
        title: "Profile" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/profile-student",
      name: "Profile student",
      component: ProfileLeerlingView,
      meta: {
        auth: true,
        title: "Profile" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/profile/edit-password",
      name: "Edit password",
      component: EditPasswordView,
      meta: {
        auth: true,
        title: "Edit password" + APP_NAME_SUFFIX + APP_NAME
      }
    },
    {
      path: "/more-information",
      name: "More information",
      component: MoreInformationView,
      meta: {
        auth: true,
        title: "More information" + APP_NAME_SUFFIX + APP_NAME
      }
    },
  ],
});

/**
 * Before each page is loaded into the DOM, run this function.
 * The rest speaks for itself.
 */
router.beforeEach(async (to) => {
  document.title = to.meta.title;

  const authenticatedAccess = to.meta.auth;

  if (authenticatedAccess) {
    console.log("Should be authenticated");

    if (AuthenticationService.authenticated() === false) {
      await router.push("/login");
    }
  } else {
    if (AuthenticationService.authenticated() === true) {
      await router.push("/dashboard");
    }
  }
});

export default router;
