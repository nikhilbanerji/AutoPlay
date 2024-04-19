### Dependencies: !pip -q install transformers==4.26.0 and !pip -q install sentencepiece

import nltk
import regex as re
from transformers import T5Tokenizer, T5ForConditionalGeneration

nltk.download('punkt')

# Load the language model and its tokenizer.
tokenizer = T5Tokenizer.from_pretrained("google/flan-t5-small")
model = T5ForConditionalGeneration.from_pretrained("google/flan-t5-small")

import nltk
from nltk.tokenize import word_tokenize

# Define feature mappings
feature_keywords = {
    'energy': {
        'high': {'words': [
    "energetic", "exciting", "lively", "upbeat", "pumping", 
    "fast-paced", "powerful", "driving", "bouncy", "fierce", 
    "thrilling", "rousing", "intense", "vibrant", "dynamic", 
    "explosive", "electrifying", "high-tempo", "aggressive", "robust", "high", "fast"
], 'value': "[0.55 TO *]"},
        'low': {'words': [
    "calm", "mellow", "soft", "slow", "quiet", 
    "gentle", "soothing", "relaxing", "serene", "peaceful", 
    "chill", "laid-back", "easygoing", "subdued", "tranquil", 
    "ambient", "light", "minimalist", "acoustic", "unplugged"
], 'value': "[0 TO 0.4]"}
    },
    'acousticness': {
        'high': {'words': [
    "acoustic", "unplugged", "natural", "organic", "raw", 
    "live", "folk", "classical", "strings", "woodwind", 
    "piano", "guitar", "violin", "minimalist", "vocal", 
    "soft rock", "jazz", "blues", "world", "real"
], 'value': "[0.5 TO *]"},
        'low': {'words': [
    "electronic", "synthesized", "amplified", "digital", "processed", 
    "electric", "synth", "techno", "EDM", "auto-tuned", 
    "pop", "hip-hop", "industrial", "metal", "dance", 
    "beat-driven", "high-tech", "produced", "studio-enhanced", "artificial"
], 'value': "[0 TO 0.4]"}
    },
    'valence': {
        'high': {'words': [
    "happy", "joyful", "cheerful", "uplifting", "bright",
    "sunny", "energetic", "fun", "bubbly", "playful",
    "exciting", "lighthearted", "positive", "upbeat", "vibrant",
    "radiant", "feel-good", "euphoric", "blissful", "delightful"
], 'value': "[0.8 TO *]"},
        'low': {'words': [
    "sad", "melancholic", "depressing", "somber", "mournful", 
    "gloomy", "dark", "bleak", "sorrowful", "moody", 
    "brooding", "melancholy", "pensive", "downtempo", "haunting", 
    "heartbreaking", "emotional", "bittersweet", "wistful", "introspective"
], 'value': "[0 TO 0.6]"}
    },
    'danceability': {
        'high': {'words': [
    "dance", "funky", "groovy", "upbeat", "rhythmic",
    "energetic", "pumping", "bouncy", "club", "disco",
    "beat-driven", "catchy", "electronic", "pop", "house",
    "techno", "driving", "party", "fast-paced", "dynamic"
], 'value': "[0.7 TO *]"},
        'low': {'words': [
    "slow", "ballad", "ambient", "minimalist", "relaxing",
    "calm", "soft", "lyrical", "orchestral", "acoustic",
    "meditative", "introspective", "ethereal", "chill", "serene",
    "mellow", "unhurried", "solemn", "contemplative", "classical"
], 'value': "[0 TO 0.35]"}
    },
    'loudness': {
        'high': {'words': [
    "loud", "powerful", "intense", "booming", "thunderous",
    "forceful", "overwhelming", "blasting", "heavy", "amplified",
    "deafening", "strong", "robust", "dominant", "pounding",
    "sonic", "earsplitting", "massive", "resounding", "explosive"
], 'value': "[-2.5 TO 0]"},
        'low': {'words': [
    "soft", "quiet", "gentle", "subtle", "low",
    "hushed", "muted", "understated", "whispering", "faint",
    "delicate", "serene", "tranquil", "peaceful", "soothing",
    "tender", "calm", "mellow", "reserved", "ambient"
], 'value': "[-10 TO -3]"}
    },
    'instrumentalness': {
        'high': {'words': [
    "instrumental", "orchestral", "symphony", "electronica", "ambient",
    "classical", "jazz", "solo", "acoustic instrumental", "synth",
    "electronic", "beats", "soundtrack", "score", "percussion",
    "piano piece", "guitar solo", "drone", "experimental", "new age"
], 'value': "[0.5 TO *]"},
        'low': {'words': [
    "vocal", "singing", "lyrics", "spoken word", "rap",
    "choral", "vocal-heavy", "lyrically rich", "narrative", "ballad",
    "pop", "hip-hop", "folk", "country", "acapella",
    "vocal-driven", "harmonious", "melodic", "soulful", "verse"
], 'value': "[0.001 TO 0.4]"}
    },
    'tempo': {
        'high': {'words': [
    "fast-paced", "upbeat", "quick", "rapid", "speedy",
    "energetic", "lively", "brisk", "accelerated", "intense",
    "driving", "frenetic", "racing", "thrilling", "bustling",
    "hurried", "fast", "swift", "turbo", "hyper"
], 'value': "[100 TO *]"},
        'low': {'words': [
    "slow", "leisurely", "languid", "unhurried", "gentle",
    "serene", "mellow", "relaxed", "soothing", "calm",
    "sluggish", "sedate", "placid", "chilled", "tranquil",
    "soft", "quiet", "deliberate", "easy", "moderate"
], 'value': "[0 TO 98]"}
    }
}

# Function to process input text
def process_text(input_text):
    tokens = word_tokenize(input_text.lower())
    return tokens

# Function to extract features based on tokens
def extract_features(tokens):
    features = {
        'danceability': "",
        'energy': "",
        'loudness': "",
        'acousticness': "",
        'instrumentalness': "",
        'valence': ""
    }
    
    for feature, options in feature_keywords.items():
        for level, info in options.items():
            if any(word in tokens for word in info['words']):
                features[feature] = info['value']
    
    return features

# Example usage
input_text = '''Input: I own a fast food hamburger restaurant called Dave's Burgers.
We are a family owned business that takes great pride in our service. We have free peanuts on each table for our guests. I want our customers to feel welcome and like they're part of the family.'''

def promptT5(user_description, promptOfInterest):
  promptDict = {"quickOrSlow": "should they play quick or slow music:\n",
                "highOrLowEnergy" : "should they play high or low energy music:\n",
                "happyOrSad": "should they play relaxing, happy, or sad music:\n",
                "danceability": "is this a place for dancing:\n",
                "loudness": "is this loud or soft music\n"}

  input_text = "At the place described in the following paragraph, " + promptDict[promptOfInterest] + user_description + "\nAnswer:"

  input_ids = tokenizer(input_text, return_tensors="pt").input_ids
  outputs = model.generate(input_ids, max_new_tokens = 20, output_scores = True, return_dict_in_generate = True)

  aslist = outputs.sequences[0].numpy().tolist()
  out_token = tokenizer.convert_ids_to_tokens(aslist, skip_special_tokens=True)[0]
  out_token = ''.join(filter(str.isalpha, out_token))
  
  return out_token 


# this is how to call T5 for different prompts and inputs

def dance(base_inputs):
  yesDance = promptT5(base_inputs, "danceability")
  if yesDance:
    return "dance"
  else:
    return "chill"

def createQuery(featuresDict):
    queryStr = ""
    for key, val in featuresDict.items():
      if val != "":
        toAdd = key + ": " + val
        queryStr = queryStr + toAdd + " AND "
    return queryStr[:-5]


def CreateStrToTokenize(inputs):
    st = ""
    st = st + " " + promptT5(inputs, "happyOrSad")
    st = st + " " + promptT5(inputs, "quickOrSlow")
    st = st + " " + dance(inputs)
    st = st + " " + promptT5(inputs, "highOrLowEnergy")
    st = st + " " + promptT5(inputs, "loudness")
    input_text = inputs + " " + st
    tokens = process_text(input_text)
    features = extract_features(tokens)
    return createQuery(features)

base_inputs = '''Input: I own a fast food hamburger restaurant called Dave's Burgers.
We are a family owned business that takes great pride in our service. We have free peanuts on each table for our guests. I want our customers to feel welcome and like they're part of the family.'''

# This is how to create the query text
# Substitute the "base_inputs" variable for the user's text
CreateStrToTokenize(base_inputs)
