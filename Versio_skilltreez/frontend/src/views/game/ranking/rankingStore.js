import { reactive } from 'vue'

function shuffle(array) {
  let currentIndex = array.length;

  // While there remain elements to shuffle...
  while (currentIndex != 0) {

    // Pick a remaining element...
    let randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex--;

    // And swap it with the current element.
    [array[currentIndex], array[randomIndex]] = [
      array[randomIndex], array[currentIndex]];
  }
}

export const rankingStore = reactive({
    cards: [],
    extraCards: [],
    getAllCards: function () {
        const allCards = [...this.cards, ...this.extraCards];
        shuffle(allCards);
        return allCards;
    }
})